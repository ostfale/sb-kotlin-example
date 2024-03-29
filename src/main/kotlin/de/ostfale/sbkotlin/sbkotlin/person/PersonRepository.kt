package de.ostfale.sbkotlin.sbkotlin.person

import org.springframework.stereotype.Repository

/**
 * Example for a in-memory repository class written in Kotlin
 * Created :  16.08.2019
 *
 *  @author : Uwe Sauerbrei
 */
@Repository
class PersonRepository {

    val persons = ArrayList<Person>()

    /**
     * Returns the single element matching the given predicate, or `null` if element
     * was not found or more than one element was found.
     */
    fun findById(id: Int): Person? {
        return persons.singleOrNull { it.id == id }
    }

    fun findAll(): List<Person> {
        return persons
    }

    fun save(person: Person): Person {
        person.id = (persons.maxBy { it.id!! }?.id ?: 0) + 1
        persons.add(person)
        return person
    }

    fun update(person: Person): Person {
        val index = persons.indexOfFirst { it.id == person.id }
        if (index >= 0) {
            persons[index] = person
        }
        return person
    }

    fun removeById(id: Int): Boolean {
        return persons.removeIf { it.id == id }
    }
}