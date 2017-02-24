package com.aude.sharding.parser.mysql;

import com.aude.sharding.test.TableBuilder;
import com.aude.sharding.parser.SqlParser;
import com.aude.sharding.parser.SqlParserFactory;
import com.aude.sharding.parser.model.ParseResult;
import com.aude.sharding.parser.model.SqlType;
import com.aude.sharding.router.RouteService;
import com.aude.sharding.router.strategy.PartitionTable;
import com.aude.sharding.sql.InsertTestSql;
import com.aude.sharding.support.BaymaxContext;
import com.aude.sharding.test.PrintUtil;
import com.aude.sharding.utils.NewArrayList;
import org.junit.Test;

/**
 * Created by sidawei on 16/4/9.
 */
public class MySqlInsertParserTest {
    private RouteService routeService = new RouteService();
    SqlParser parser = SqlParserFactory.getParser(SqlType.INSERT);

    {
        ininContext();
    }

    /*--------------------------------------------------single------------------------------------------------------*/

    private void ininContext(){
        PartitionTable table = new TableBuilder()
                .appenTable("table1", "table1_{0}", "value % 4")
                .appendNodeMapping("p1:0,1,2,3")
                .appendColumn("a", null)
                .appendColumn("b", null)
                .toTable();

        BaymaxContext.setTables(NewArrayList.newIt().add(table).toArrayList());

        BaymaxContext.init();
    }

    /*--------------------------------------------------single------------------------------------------------------*/

    @Test
    public void testParse() throws Exception {
        test(InsertTestSql.i1);

    }

    public void test(String sql) throws Exception {
        ParseResult result = new ParseResult();

        parser.init(sql, null);

        parser.parse(result);

        System.out.println();
        System.out.println(sql);
        System.out.println(result.getTableAliasMap());
        String unitStr2 = PrintUtil.printCalculates(result.getCalculateUnits());
    }
}