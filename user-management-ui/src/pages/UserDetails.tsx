import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useQuery } from 'react-query';
import {
  Container,
  Paper,
  Typography,
  Grid,
  Avatar,
  Button,
  CircularProgress,
  Box,
} from '@mui/material';
import { getUserById } from '@/services/api';

export const UserDetails: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  const { data: user, isLoading, error } = useQuery(
    ['user', id],
    () => getUserById(Number(id)),
    {
      enabled: !!id,
    }
  );

  if (isLoading) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="80vh">
        <CircularProgress />
      </Box>
    );
  }

  if (error || !user) {
    return (
      <Container maxWidth="md">
        <Paper sx={{ p: 3, mt: 3 }}>
          <Typography variant="h6" color="error">
            Error loading user details
          </Typography>
          <Button onClick={() => navigate('/')} sx={{ mt: 2 }}>
            Back to Search
          </Button>
        </Paper>
      </Container>
    );
  }

  return (
    <Container maxWidth="md">
      <Paper sx={{ p: 3, mt: 3 }}>
        <Grid container spacing={3}>
          <Grid item xs={12} md={4} display="flex" justifyContent="center">
            <Avatar
              src={user.image}
              alt={`${user.firstName} ${user.lastName}`}
              sx={{ width: 200, height: 200 }}
            />
          </Grid>
          <Grid item xs={12} md={8}>
            <Typography variant="h4" gutterBottom>
              {user.firstName} {user.lastName}
            </Typography>
            <Typography variant="body1" color="text.secondary" paragraph>
              <strong>Email:</strong> {user.email}
            </Typography>
            <Typography variant="body1" color="text.secondary" paragraph>
              <strong>SSN:</strong> {user.ssn}
            </Typography>
            {user.phone && (
              <Typography variant="body1" color="text.secondary" paragraph>
                <strong>Phone:</strong> {user.phone}
              </Typography>
            )}
            {user.address && (
              <Typography variant="body1" color="text.secondary" paragraph>
                <strong>Address:</strong> {user.address}
              </Typography>
            )}
            {user.gender && (
              <Typography variant="body1" color="text.secondary" paragraph>
                <strong>Gender:</strong> {user.gender}
              </Typography>
            )}
            {user.description && (
              <Typography variant="body1" color="text.secondary" paragraph>
                <strong>Description:</strong> {user.description}
              </Typography>
            )}
          </Grid>
        </Grid>
        <Box mt={3}>
          <Button onClick={() => navigate('/')}>Back to Search</Button>
        </Box>
      </Paper>
    </Container>
  );
}; 