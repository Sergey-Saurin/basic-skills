import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://lenta.ru/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Список имен скачанных файлов
        List<String> nameFileImage = new ArrayList<>();

        for(Element e : doc.select("img")){
           String fileName = e.attr("abs:src").substring(e.attr("abs:src").lastIndexOf('/') + 1);
           String fileNameNoSpecialSymbol = fileName.replaceAll("[\\/*:?\"<>|]", "_");

           /**Правильно понял try with resource, добавил tru(FileOutputStream...) и теперь после выполнения блока try
           файл закроется и поэтому close() подсветилось серым цветом(пометил ниже комментом "здесь")
           Немного не понятно для чего это, в документации написано чтоб сохранялись все ошибки которые произошли в блоке
           try(тут ошибка) {тут ошибка} или я не так понял?Но ведь если произошда первая ошибка вторая нам не нужна
           так как вторая произошла из-за первой.
            ////////////////////////////////////////////////////
           Второе что думаю, я не так понял про несколько ошибок и назначение try with resource чтоб закрывать файл, (без сохранения всех ошибок в блоке)
           в случаи если в блоке try() произошла ошибка до выполнения кода по закрытию файла out.close()
           освобождая этим память*/

           try(FileOutputStream out = (new FileOutputStream(new File("C:\\Users\\Sergey\\Desktop\\src\\" +fileNameNoSpecialSymbol)))){
                byte[] bytes = Jsoup.connect(e.attr("abs:src")).ignoreContentType(true).execute().bodyAsBytes();
                out.write(bytes);
               /**"здесь"
                //out.close();*/
           } catch (IOException ioException) {
               ioException.printStackTrace();
           }

           //если попвтка не прервалась значит файл скачан
           nameFileImage.add(fileNameNoSpecialSymbol);

        }

        for(String el : nameFileImage) {
            System.out.println(el);
        }
    }
}
