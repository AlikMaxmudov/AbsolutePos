import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceManager {

    // Вернусь позже сюда.

    private const val SHARED_PREFS_NAME = "user_data"

    fun saveString(context: Context, key: String, value: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveInt(context: Context, key: String, value: Int) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }
}