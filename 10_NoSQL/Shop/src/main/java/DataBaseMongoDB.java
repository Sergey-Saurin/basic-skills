import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.BsonDocument;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Filters.*;

public class DataBaseMongoDB
{
    private MongoDatabase database;
    private MongoCollection<Document> products;
    private MongoCollection<Document>shops;

    public DataBaseMongoDB(String host, int port)
    {
        MongoClient mongoClient = new MongoClient( host , port );

        this.database = mongoClient.getDatabase("local");

        this.products = database.getCollection("products");
        this.shops = database.getCollection("shops");
        products.drop();
        shops.drop();

    }

    public void addProduct(String nameProduct, int price) {
        //Проверка существует ли такой товар, если да нужно обновить цену, чтоб не менялся id, а цена новая, на случай переоценки
        Document docProduct = productIsExist(nameProduct);
        if (docProduct == null) {
            products.insertOne(new Document("name", nameProduct).append("price", price));
        }else
        {
            Bson filter = eq("_id", docProduct.get("_id"));
            Bson updates = Updates.set("price", price);
            products.findOneAndUpdate(filter, updates);
        }
    }

    public void addShop(String nameShop) {
        //Проверка существует ли такой магазин
        Document docShop = shopIsExist(nameShop);
        if (docShop == null)
        {
            shops.insertOne(new Document("name", nameShop).append("products", new ArrayList<>()));
        }
    }


    public void addProductInShop(String nameProduct, String nameShop) {
        Document docProduct = productIsExist(nameProduct);
        Document docShop = shopIsExist(nameShop);
        if ( docShop != null && docProduct != null) {
            Bson filter = eq("_id", docShop.get("_id"));
            Bson updates = Updates.addToSet("products", docProduct);
            shops.findOneAndUpdate(filter, updates);

        }
    }

    private Document productIsExist(String nameProduct)
    {
        BsonDocument query = BsonDocument.parse("{name: \"" + nameProduct + "\"}");
        return products.find(query).first();
    }
    private Document shopIsExist(String nameShop)
    {
        BsonDocument query = BsonDocument.parse("{name: \"" + nameShop + "\"}");
        return shops.find(query).first();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getStatistic() {

        String statistics = "Statistics empty";
        StringBuilder stringBuilder = new StringBuilder();

        FindIterable<Document> allShops = shops.find();
        for (Document shop : allShops) {

            //общее количество товаров,
            int productsCount = ((ArrayList<?>) shop.get("products")).size();

            if (productsCount != 0) {

                //Средняя цена
                Bson match = match(eq("name", shop.get("name")));
                Bson lookup = lookup("products", "products.name", "name", "ProductsInShop");
                Bson unwind = unwind("$ProductsInShop");
                Bson groupAverage = group("$name", avg("Average", "$ProductsInShop.price"));

                Document average = shops.aggregate(Arrays.asList(match, lookup, unwind, groupAverage)).first();

                //Самый дешевый товар
                Bson groupCheapest = group("$price", min("minPrice", "$ProductsInShop.price"));
                Document cheapestProduct = shops.aggregate(Arrays.asList(match, lookup, unwind, groupCheapest)).first();

                //Самый дорогой товар
                Bson groupExpensivest = group("$price", max("maxPrice", "$ProductsInShop.price"));
                Document expensivestProduct = shops.aggregate(Arrays.asList(match, lookup, unwind, groupExpensivest)).first();

                //Товар меньше 100
                Bson matchLess100 = match(lt("ProductsInShop.price", 100));
                Bson groupLess100 = group("$name", sum("count", 1));
                Document priceLess100 = shops.aggregate(Arrays.asList(match, lookup, unwind,matchLess100, groupLess100)).first();



                statistics = stringBuilder.append("\n\nSHOP " + shop.get("name"))
                        .append("\nTotal number of products: " + productsCount)
                        .append("\nAverage price: " + average.get("Average"))
                        .append("\nCheapest product: " + cheapestProduct.get("minPrice"))
                        .append("\nExpensivest product: " + expensivestProduct.get("maxPrice"))
                        .append("\nPrice less 100: " + priceLess100.get("count")).toString();

            }

        }
        return statistics;

    }
    private String arrayToString(ArrayList<String> shopItems)
    {
        String shopItemsString = shopItems.toString().replace(", ", "\", \"");
        String shopItemsString2 = shopItemsString.replace("[", "[\"");
        String shopItemsString3 = shopItemsString2.replace("]", "\"]");
        return shopItemsString3;
    }
}
