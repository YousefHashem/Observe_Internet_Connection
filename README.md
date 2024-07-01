# Connectivity Monitor

Connectivity Monitor is an Android application designed to observe internet connectivity and provide user feedback through a Snackbar. This project demonstrates the use of MVVM architecture, repositories, ViewModels, and UI components in a clean and organized manner.

Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

Features

- Monitors internet connectivity status.
- Provides visual feedback to the user using a Snackbar.
- Utilizes MVVM architecture for better separation of concerns and testability.
- Clean and modular project structure.

Architecture

The project is structured using the Model-View-ViewModel (MVVM) architecture pattern. The main components include:

- **Repository**: Manages data operations.
- **ViewModel**: Holds UI-related data and business logic.
- **UI**: Handles the presentation logic and user interactions.

Project Structure

```
com.yousefh.devona.app
├── repository
│   └── ConnectivityRepository.kt
├── viewmodel
│   ├── ObserveInternetConnectivityViewModel.kt
│   └── ObserveInternetConnectivityViewModelFactory.kt
└── ui
    └── SnackBar.kt
```

- `repository`: Contains `ConnectivityRepository.kt`, which manages internet connectivity status.
- `viewmodel`: Contains `ObserveInternetConnectivityViewModel.kt` and `ObserveInternetConnectivityViewModelFactory.kt`, which manage and provide connectivity status to the UI.
- `ui`: Contains `SnackBar.kt`, which displays a Snackbar with a custom message and colors.

Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/devona-connectivity-monitor.git
   cd devona-connectivity-monitor
   ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

Usage

1. Initialize the `SnackBar` class in your activity or fragment:

   ```kotlin
   val rootView: View = findViewById(R.id.root_view)
   val snackBar = SnackBar(context, rootView)
   ```

2. Show a Snackbar message:

   ```kotlin
   snackBar.showSnackBar("This is a Snackbar message", R.color.snackbar_background, R.color.snackbar_text)
   ```

3. Observe internet connectivity status in your ViewModel:

   ```kotlin
   val connectivityRepository = ConnectivityRepository(applicationContext)
   val viewModelFactory = ObserveInternetConnectivityViewModelFactory(connectivityRepository)
   val viewModel = ViewModelProvider(this, viewModelFactory).get(ObserveInternetConnectivityViewModel::class.java)

   viewModel.isConnected.observe(this, Observer { isConnected ->
       if (isConnected) {
           // Connected
       } else {
           // Disconnected
       }
   })
   ```
