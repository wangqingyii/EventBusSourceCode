
package org.greenrobot.eventbus;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 指定被该元注解修饰的注解类将被javadoc工具提取成文档
@Documented
// 注解存活时间，编译器把注解记录在class文件中。当程序运行时，JVM也可获取注解信息，
// 程序可以通过反射获取该注解信息。
@Retention(RetentionPolicy.RUNTIME)
// 修饰注解定义。指定该策略的直接只能修饰方法定义
@Target({ElementType.METHOD})
public @interface Subscribe {
    // 返回线程模式
    ThreadMode threadMode() default ThreadMode.POSTING;

    /**
     * 如果为 true，则将最近的粘性事件（通过 {@link EventBuspostSticky(Object)} 发布）传递给该订阅者（如果事件可用）。
     *
     * 判断是否是粘性事件
     */
    boolean sticky() default  ;

    /**
     * 订阅者优先级影响事件传递的顺序。在同一个交付线程 ({@link ThreadMode}) 中，
     * 较高优先级的订阅者将在其他具有较低优先级的订阅者之前收到事件。默认优先级为0。
     * 注意：优先级不影响具有不同{@link ThreadMode}的订阅者之间的传递顺序！
     */
    int priority() default 0;
}

