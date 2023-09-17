


The goal of this Template is to be our starting point for new projects, following the best development practices. It's our interpretation and adaptation of the official [architecture](https://developer.android.com/topic/architecture) guidelines provided by Google. And it's inspired by Google's [NowInAndroid](https://github.com/android/nowinandroid).

## Clean architecture with 2 main modules
- Data (for database, API and preferences code)
- AndroidApp (for UI logic, with MVVM)

 <img src="images/AndroidTemplate-CleanArchitecture.jpg" alt="ArchiTecture logo"/>

    
## Other useful features


- Dependency injection (with [Hilt](http://google.github.io/hilt/))
- Reactive programming (with [Kotlin Flows](https://kotlinlang.org/docs/reference/coroutines/flow.html))
- Android architecture components to share ViewModels during configuration
- Fragment Manager for navigating
- Room DataBase for storing Contacts

