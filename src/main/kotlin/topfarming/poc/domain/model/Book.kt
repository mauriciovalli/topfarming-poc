package topfarming.poc.domain.model

import javax.persistence.*

@Entity
@Table(name = "book")
data class Book(
        @Id
        @GeneratedValue
        val id: Long,

        @Column(name = "name", nullable = false)
        val name: String,

        @Column(name = "isbn", nullable = false)
        val isbn: String,

        @ManyToOne
        val genre: Genre? = null
) {
        constructor() : this(0, "", "")
}