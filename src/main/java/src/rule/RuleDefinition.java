package src.rule;

/**
 * Created by chenyu on 2017/6/27.
 */
public enum RuleDefinition {

    SERIALIZABLE(1, "序列化规范"),
    SQLINJECT(2, "SQL注入"),
    XMLINJECT(3, "XML注入"),
    ORDERINJECT(4, "命令注入");

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    RuleDefinition(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
