import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.List;

/**
 * https://www.w3cschool.cn/shiro/
 * */
public class Program {
    public static void main(String[] args) {
        //Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        //UsernamePasswordToken token = new UsernamePasswordToken("lujun", "root");
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }

        /*System.out.println("login:");
        System.out.println("isAuthenticated: "+subject.isAuthenticated());
        PrincipalCollection principals = subject.getPrincipals();
        List list = principals.asList();
        int size = list.size();
        System.out.println("principals: "+size);
        //rbc基于角色的访问控制
        boolean hasRole = subject.hasRole("role1");
        System.out.println("role: "+hasRole);
        //rbc基于资源的访问控制（资源:操作:对象实例ID）
        boolean permittedAll = subject.isPermittedAll("user:create", "user:update", "user:delete");
        System.out.println("permittedAll: "+permittedAll);*/
        //单个资源单个权限
        /*boolean permitted = subject.isPermitted("user:create");
        System.out.println("permitted: "+permitted);*/
        //单个资源多个权限
        boolean[] permitted = subject.isPermitted("user:create","user:update");
        System.out.println("permitted0: "+permitted[0]+"\npermitted1: "+permitted[1]);

        /*subject.logout();
        System.out.println("logout");
        System.out.println("isAuthenticated: "+subject.isAuthenticated());
        hasRole = subject.hasRole("role1");
        System.out.println("role: "+hasRole);
        permittedAll = subject.isPermittedAll("user:create", "user:update", "user:delete");
        System.out.println("permittedAll: "+permittedAll);*/
    }
}
