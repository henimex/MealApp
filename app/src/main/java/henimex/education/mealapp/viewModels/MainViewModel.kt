package henimex.education.mealapp.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import henimex.education.mealapp.services.apiService
import henimex.education.mealapp.states.RecipeState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categoryState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoryState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = apiService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (exception: Exception) {
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Fetching Error! Message : ${exception.message}"
                )
            }
        }
    }
}