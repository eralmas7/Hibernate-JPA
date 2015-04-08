package com.adserver.datastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.adserver.entities.AdCategory;

public class CategoryGraphTest {

    private CategoryGraph categoryGraph;
    private List<AdCategory> categories;

    @Before
    public void setUp() throws Exception {
        categoryGraph = new CategoryGraph();
        categories = new ArrayList<AdCategory>(8);
        int[] parent = {0, 1, 2, 1, 3, 5, 6};
        for (int i = 1; i < 8; i++) {
            categories.add(new AdCategory(i, ((char) ('A' + (i - 1))) + "", parent[i - 1]));
        }
    }

    @Test
    public void testDecendantsSucessfully() {
        categoryGraph.initGraph(categories);
        final List<AdCategory> categories = categoryGraph.getDescendantCategories(3);
        final Set<Integer> expectedOutput = new HashSet<Integer>();
        for (int i = 3; i < 8; i++) {
            expectedOutput.add(i);
        }
        final Set<Integer> actualOutput = new HashSet<Integer>();
        for (AdCategory adCategory : categories) {
            actualOutput.add(adCategory.getCategoryId());
        }
        Assert.assertTrue(expectedOutput.containsAll(actualOutput));
    }

    @Test
    public void testDecendantsWithoutAnyPresent() {
        categoryGraph.initGraph(categories);
        final List<AdCategory> categories = categoryGraph.getDescendantCategories(8);
        Assert.assertTrue(categories.isEmpty());
    }

    @Test
    public void testDecendantsWithoutAnyParent() {
        categoryGraph.initGraph(categories);
        final List<AdCategory> categories = categoryGraph.getDescendantCategories(0);
        Assert.assertTrue(categories.isEmpty());
    }
}
