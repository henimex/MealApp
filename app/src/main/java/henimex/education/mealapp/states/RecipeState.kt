package henimex.education.mealapp.states

import henimex.education.mealapp.entities.Category

data class RecipeState(
    val loading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
)
