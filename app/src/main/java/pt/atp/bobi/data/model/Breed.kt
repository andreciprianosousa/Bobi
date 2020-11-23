package pt.atp.bobi.data.model

data class Breed(
    val bred_for: String,
    val breed_group: String,
    val height: Height,
    val id: Int,
    val life_span: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val weight: Weight
)