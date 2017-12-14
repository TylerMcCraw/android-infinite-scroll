# android-infinite-scroll
Demo application which aims to solve best practices for an "infinite" or "endless" scrolling newsfeed

Models will be stored in a Realm persistence layer and can be found [here](https://github.com/TylerMcCraw/android-infinite-scroll/tree/master/api/src/main/java/com/w3bshark/infinitescroll/api/data)
APIs / data sources can be found [here](https://github.com/TylerMcCraw/android-infinite-scroll/tree/master/api/src/main/java/com/w3bshark/infinitescroll/api/data/source)

All Display business logic is separated into the [NewsFeedPresenter](https://github.com/TylerMcCraw/android-infinite-scroll/blob/master/app/src/main/java/com/w3bshark/infinitescroll/newsfeed/NewsFeedPresenter.kt).
We are using an Model View Presenter architecture pattern to separate business and app logic from the UI.
This allows us to more easily test business logic without having to worry about the intricacies of the UI.

When the user scrolls the [NewsFeedActivity](https://github.com/TylerMcCraw/android-infinite-scroll/blob/master/app/src/main/java/com/w3bshark/infinitescroll/newsfeed/NewsFeedActivity.kt) and [NewsFeedAdapter](https://github.com/TylerMcCraw/android-infinite-scroll/blob/master/app/src/main/java/com/w3bshark/infinitescroll/newsfeed/NewsFeedAdapter.kt) 
will know to load more items ahead of time by checking if the last item that is visible is approaching our defined threshold. 
Currently, if there are only 5 items remaining in the list below the last currently visible item, the application will fetch more posts from the server unbeknownst to the user.
This way, it simulates an infinite/endless scroll and the user isn't affected in any way.
The user can fling their finger to scroll quickly, but the application will load more items with no visible pause (unless the network is slow).
If the network is slow to load more items, there is a progressbar that will appear as the last item in the list to visually indicate that more items are being loaded.

There are 2 modules in this project:
- `api` - Android "Backend" data models and data sources 
- `app` - Android Frontend client UI, MVP structures, all view and business logic

Goals:
Android Client Backend
1. Separate module which only exposes data sources with apis that provide immutable objects.
    1. Allows for data sources to be testable completely independent from UI
    2. Allows for easy switching out backend architecture, and as long as UI models and functions remain the same, the app shouldn’t break.
    3. Dependency Inversion Principle - UI has no knowledge of internal workings of network/caching architecture
2. Only expose data models that must be exposed and only provide data sources via Dagger module
3. OkHttpLogginInterceptor for debug builds to log network requests

Android Client UI
1. Group packages by feature
2. RecyclerView items - could optimize by using canvas and drawing own item views, but maintainability decreases due to increase in complexity here.
3. Show placeholder image view frame or animated drawable for images while they’re loading
4. Prefetch data and images with a buffer before items come into view so that users aren’t waiting on content to load
5. Splash screen - give impression that it is loading quicker on cold boots

In a real client, there would be packages for Tests in each module, but due to timing, I do not have tests built out yet.
