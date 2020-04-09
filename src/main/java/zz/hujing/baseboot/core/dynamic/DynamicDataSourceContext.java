package zz.hujing.baseboot.core.dynamic;

/**
 * 动态数据库上下文
 **/
public class DynamicDataSourceContext {

    public static final ThreadLocal<DataSourceType> dataSourceContext = new ThreadLocal<>();

    public static void setDataSourceType(DataSourceType dataSourceType) {
        dataSourceContext.set(dataSourceType);
    }

    public static DataSourceType get(){
        return dataSourceContext.get();
    }

    public static void remove(){
        dataSourceContext.remove();
    }

    public static void master(){
        dataSourceContext.set(DataSourceType.MASTER);
    }

    public enum DataSourceType{
        MASTER,SLAVER
    }
}
