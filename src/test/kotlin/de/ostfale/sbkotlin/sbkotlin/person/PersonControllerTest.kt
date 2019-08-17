package de.ostfale.sbkotlin.sbkotlin.person

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

/**
 * JUnit fest for controller functionality
 * Created :  17.08.2019
 *
 * @author : Uwe Sauerbrei
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class PersonControllerTest {

    @Autowired
    lateinit var template: TestRestTemplate

    @Test
    fun test1AddPerson() {
        var person = Person(null, "John Smith", 20, Gender.MALE)
        person = template.postForObject("/persons", person, Person::class.java)
        Assert.assertNotNull(person)
        Assert.assertNotNull(person.id)
        Assert.assertEquals(1, person.id)
    }

    @Test
    fun test2UpdatePerson() {
        var person = Person(1, "John Smith", 21, Gender.MALE)
        template.put("/persons", person)
        person = template.getForObject("/persons/{id}", Person::class.java, 1)
        Assert.assertNotNull(person)
        Assert.assertEquals(21, person.age)
    }

    @Test
    fun test3DeletePerson() {
        template.delete("/persons/{id}", 1)
        val person = template.getForObject("/persons/{id}", Person::class.java, 1)
        Assert.assertNull(person)
    }
}