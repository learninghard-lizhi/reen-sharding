package com.aude.sharding.router.model;

/**
 * 目标表
 */
public class TargetTableEntity {

    /**
     * 目标数据库
     * 取值DataSourceGroup.identity
     */
    private String targetDB;

    /**
     * 目标表名
     */
    private String targetTable;

    public TargetTableEntity(String targetDB, String targetTable) {
        this.targetDB = targetDB;
        this.targetTable = targetTable;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TargetTableEntity){
            TargetTableEntity entity = (TargetTableEntity) o;
            if (targetDB != null
                    && targetTable != null
                    && targetDB.equals(entity.getTargetDB())
                    && targetTable.equals(entity.getTargetTable())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return targetDB.hashCode() + targetTable.hashCode();
    }


    public String getTargetDB() {
        return targetDB;
    }

    public void setTargetDB(String targetDB) {
        this.targetDB = targetDB;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    @Override
    public String toString() {
        return String.format("{DB:%s, Table:%s}", new String[]{targetDB, targetTable});
    }
}