package src.model;

/**
 * Created by chenyu on 2017/6/27.
 */
public enum RuleDefinition {

    SERIALIZABLE(1, "序列化规范"),
    SQLINJECT(2, "SQL注入"),
    XXEINJECT(3, "XXE注入"),
    COMMANDINJECT(4, "命令注入"),
    PASSWORDHARDCODE(5, "密码硬编码"),
    FILENOTSECURE(6,"文件不安全访问"),
    SPRINGBOOTSPELINJECT(7,"springboot版本不安全");

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
