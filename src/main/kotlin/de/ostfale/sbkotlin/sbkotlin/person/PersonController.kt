package de.ostfale.sbkotlin.sbkotlin.person

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *  In Kotlin, every property declared as having non-null type must be initialized in the constructor.
 *  So, if you are initializing it using dependency injection it has to declared as lateinit.
 * Created :  16.08.2019
 *
 *  @author : Uwe Sauerbrei
 */
@RestController
@RequestMapping("/persons")
class PersonController {

    @Autowired
    lateinit var repository: PersonRepository

    /**
     * Annotation @PathVariable does not require any arguments. The input parameter name is
     * considered to be the same as variable name.
     */
    @GetMapping("/id")
    fun findById(@PathVariable id: Int): Person? = repository.findById(id)

    @GetMapping
    fun findAll(): List<Person> = repository.findAll()

    @PostMapping
    fun add(@RequestBody person: Person): Person = repository.save(person)

    @PutMapping
    fun update(@RequestBody person: Person): Person = repository.update(person)

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Int): Boolean = repository.removeById(id)
}