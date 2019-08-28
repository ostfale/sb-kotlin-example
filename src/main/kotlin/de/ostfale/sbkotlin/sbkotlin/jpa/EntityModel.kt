package de.ostfale.sbkotlin.sbkotlin.jpa

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToOne

/**
 * Entity model classes
 * Created :  20.08.2019
 *
 *  @author : Uwe Sauerbrei
 */
@Entity
class Person(
        val name: String,
        @OneToOne(cascade = [(CascadeType.ALL)], orphanRemoval = true, fetch = FetchType.EAGER)
        val address: Address
) : AbstractJpaPersistable<Long>()

@Entity
class Address(
        val street: String,
        val zipCode: String,
        val city: String
) : AbstractJpaPersistable<Long>()