# User Management UI

A React-based frontend application for the User Management Service. This application provides a modern, responsive interface with typeahead/autocomplete search functionality for users.

## Features

- Typeahead/autocomplete search for users
- Real-time search with debouncing
- Responsive design using Material-UI
- Detailed user profile view
- Clean and modern UI
- Type-safe development with TypeScript
- Unit tests with React Testing Library

## Prerequisites

- Node.js 14.x or higher
- npm 6.x or higher
- Backend service running on http://localhost:8080

## Installation

1. Install dependencies:
```bash
npm install
```

2. Create a `.env` file in the root directory with the following content:
```
VITE_API_BASE_URL=http://localhost:8080/api
VITE_SEARCH_DEBOUNCE_MS=300
VITE_MIN_SEARCH_CHARS=3
```

## Development

To run the application in development mode:

```bash
npm run dev
```

The application will start on http://localhost:5173

## Building for Production

To create a production build:

```bash
npm run build
```

The build artifacts will be stored in the `dist/` directory.

## Testing

To run the test suite:

```bash
npm test
```

To generate a coverage report:

```bash
npm run coverage
```

## Project Structure

```
src/
  ├── components/     # Reusable UI components
  ├── pages/         # Page components
  ├── services/      # API services
  ├── hooks/         # Custom React hooks
  ├── types/         # TypeScript type definitions
  ├── utils/         # Utility functions
  ├── styles/        # Global styles
  ├── App.tsx        # Main application component
  └── main.tsx       # Application entry point
```

## Key Features

1. **Typeahead Search**
   - Real-time search with debouncing
   - Minimum 3 characters required
   - Displays user name, SSN, and email in results

2. **User Details**
   - Comprehensive user information display
   - Responsive layout
   - User image display
   - Easy navigation back to search

3. **Responsive Design**
   - Mobile-first approach
   - Adaptive layout for different screen sizes
   - Material-UI components for consistent styling

4. **Performance**
   - Lazy loading of routes
   - Debounced API calls
   - Caching of search results

## Best Practices

- Clean code architecture
- Component-based development
- Type safety with TypeScript
- Proper error handling
- Comprehensive testing
- Code splitting and lazy loading
- Modern React patterns (hooks, context)

## Environment Variables

- `VITE_API_BASE_URL`: Backend API base URL
- `VITE_SEARCH_DEBOUNCE_MS`: Debounce delay for search
- `VITE_MIN_SEARCH_CHARS`: Minimum characters for search

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request 