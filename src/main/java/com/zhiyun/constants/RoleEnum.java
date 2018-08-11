package com.zhiyun.constants;

/**
 * @Auther: sunyuntao
 * @Date: 2018/08/11 14:55
 * @Description:
 */
public enum RoleEnum {

    Guest(20122L,"Guest","游客"),User(20125L,"User","实名用户"),
    Operator(223736L,"Operator","运营商"),EnterUser(223738L,"EnterUser","入驻用户"),
    Agent(247091L,"Agent","代理商");

    private Long roleId;

    private String name;

    private String label;

    RoleEnum(Long roleId,String name,String label){
        this.roleId = roleId;
        this.name = name;
        this.label = label;
    }

    public static String traslateRoleName(String name){
        for (RoleEnum role : RoleEnum.values()) {
            if(role.name.equals(name)){
                return role.label;
            }
        }
        return null;
    }

}
