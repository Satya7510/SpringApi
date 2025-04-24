import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ThemeProvider, CssBaseline, Container, AppBar, Toolbar, Typography } from '@mui/material';
import { createTheme } from '@mui/material/styles';
import { UserSearch } from '@/components/UserSearch';
import { UserDetails } from '@/pages/UserDetails';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 1,
    },
  },
});

const theme = createTheme({
  palette: {
    mode: 'light',
    primary: {
      main: '#1976d2',
    },
  },
});

const App: React.FC = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Router>
          <AppBar position="static">
            <Toolbar>
              <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                User Management
              </Typography>
            </Toolbar>
          </AppBar>
          <Container sx={{ mt: 4 }}>
            <Routes>
              <Route path="/" element={<UserSearch />} />
              <Route path="/users/:id" element={<UserDetails />} />
            </Routes>
          </Container>
        </Router>
      </ThemeProvider>
    </QueryClientProvider>
  );
};

export default App; 