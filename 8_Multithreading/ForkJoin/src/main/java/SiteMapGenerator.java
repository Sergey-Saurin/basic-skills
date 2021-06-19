import java.util.concurrent.CopyOnWriteArrayList;

public class SiteMapGenerator
{

    private volatile SiteMapGenerator parent;

    private volatile int level;
    private String url;
    private volatile CopyOnWriteArrayList<SiteMapGenerator> children;

    public SiteMapGenerator(String url) {
        level = 0;
        this.url = url;
        parent = null;
        children = new CopyOnWriteArrayList<>();
    }

    private int calculateLevel() {
        int result = 0;
        if (parent == null) {
            return result;
        }
        result = 1 + parent.getLevel();
        return result;
    }

    public synchronized void addChild(SiteMapGenerator element) {
        SiteMapGenerator parent = getParent();
        if(!parent.contains(element.getUrl())) {
            element.setParent(this);
            children.add(element);
        }
    }

    private boolean contains(String url) {
        if (this.url.equals(url)) {
            return true;
        }
        for (SiteMapGenerator child : children) {
            if(child.contains(url))
                return true;
        }

        return false;
    }

    public String getUrl() {
        return url;
    }

    private void setParent(SiteMapGenerator sitemapNode) {
        synchronized (this) {
            this.parent = sitemapNode;
            this.level = calculateLevel();
        }
    }

    public SiteMapGenerator getParent() {
        return parent == null ? this : parent.getParent();
    }

    public CopyOnWriteArrayList<SiteMapGenerator> getChildren() {
        return children;
    }

    public int getLevel() {
        return level;
    }

}
