// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.OscePostDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OscePostIntegrationTest_Roo_IntegrationTest {
    
    declare @type: OscePostIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: OscePostIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: OscePostIntegrationTest: @Transactional;
    
    @Autowired
    private OscePostDataOnDemand OscePostIntegrationTest.dod;
    
    @Test
    public void OscePostIntegrationTest.testCountOscePosts() {
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to initialize correctly", dod.getRandomOscePost());
        long count = ch.unibas.medizin.osce.domain.OscePost.countOscePosts();
        org.junit.Assert.assertTrue("Counter for 'OscePost' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void OscePostIntegrationTest.testFindOscePost() {
        ch.unibas.medizin.osce.domain.OscePost obj = dod.getRandomOscePost();
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.OscePost.findOscePost(id);
        org.junit.Assert.assertNotNull("Find method for 'OscePost' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'OscePost' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void OscePostIntegrationTest.testFindAllOscePosts() {
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to initialize correctly", dod.getRandomOscePost());
        long count = ch.unibas.medizin.osce.domain.OscePost.countOscePosts();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'OscePost', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<ch.unibas.medizin.osce.domain.OscePost> result = ch.unibas.medizin.osce.domain.OscePost.findAllOscePosts();
        org.junit.Assert.assertNotNull("Find all method for 'OscePost' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'OscePost' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void OscePostIntegrationTest.testFindOscePostEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to initialize correctly", dod.getRandomOscePost());
        long count = ch.unibas.medizin.osce.domain.OscePost.countOscePosts();
        if (count > 20) count = 20;
        java.util.List<ch.unibas.medizin.osce.domain.OscePost> result = ch.unibas.medizin.osce.domain.OscePost.findOscePostEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'OscePost' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'OscePost' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void OscePostIntegrationTest.testFlush() {
        ch.unibas.medizin.osce.domain.OscePost obj = dod.getRandomOscePost();
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.OscePost.findOscePost(id);
        org.junit.Assert.assertNotNull("Find method for 'OscePost' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyOscePost(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'OscePost' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void OscePostIntegrationTest.testMerge() {
        ch.unibas.medizin.osce.domain.OscePost obj = dod.getRandomOscePost();
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.OscePost.findOscePost(id);
        boolean modified =  dod.modifyOscePost(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        ch.unibas.medizin.osce.domain.OscePost merged =  obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'OscePost' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void OscePostIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to initialize correctly", dod.getRandomOscePost());
        ch.unibas.medizin.osce.domain.OscePost obj = dod.getNewTransientOscePost(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'OscePost' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'OscePost' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void OscePostIntegrationTest.testRemove() {
        ch.unibas.medizin.osce.domain.OscePost obj = dod.getRandomOscePost();
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'OscePost' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.OscePost.findOscePost(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'OscePost' with identifier '" + id + "'", ch.unibas.medizin.osce.domain.OscePost.findOscePost(id));
    }
    
}
