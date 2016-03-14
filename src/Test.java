/**
 * Created by david SALMON on 11/03/2016.
 */
public class Test {

    private String attr1;
    private String att2;

    public Test() {
    }

    public Test(String att2) {
        this.att2 = att2;
    }

    public Test(String attr1, String att2) {
        this.attr1 = attr1;
        this.att2 = att2;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAtt2() {
        return att2;
    }

    public void setAtt2(String att2) {
        this.att2 = att2;
    }
}
