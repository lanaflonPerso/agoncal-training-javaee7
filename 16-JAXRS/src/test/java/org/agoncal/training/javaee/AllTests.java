package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.BookTest;
import org.agoncal.training.javaee.model.CDTest;
import org.agoncal.training.javaee.service.IsbnGeneratorTest;
import org.agoncal.training.javaee.service.ItemServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ItemServiceTest.class,
        IsbnGeneratorTest.class,
        BookTest.class,
        CDTest.class
})
public class AllTests {
}