import React from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Autocomplete,
  TextField,
  CircularProgress,
  Paper,
  Typography,
  Box,
} from '@mui/material';
import { useUserSearch } from '@/hooks/useUserSearch';
import { UserSearchResult } from '@/types/user';

export const UserSearch: React.FC = () => {
  const navigate = useNavigate();
  const { searchTerm, results, isLoading, handleSearchChange } = useUserSearch();

  const handleOptionSelect = (_: any, option: UserSearchResult | null) => {
    if (option) {
      navigate(`/users/${option.id}`);
    }
  };

  return (
    <Autocomplete
      sx={{ width: '100%', maxWidth: 600 }}
      options={results}
      getOptionLabel={(option: UserSearchResult) => 
        `${option.firstName} ${option.lastName} (${option.ssn})`
      }
      filterOptions={(x) => x} // Disable client-side filtering
      onChange={handleOptionSelect}
      onInputChange={(_, value) => handleSearchChange(value)}
      inputValue={searchTerm}
      loading={isLoading}
      noOptionsText={
        searchTerm.length < Number(import.meta.env.VITE_MIN_SEARCH_CHARS)
          ? `Enter at least ${import.meta.env.VITE_MIN_SEARCH_CHARS} characters`
          : 'No users found'
      }
      renderInput={(params) => (
        <TextField
          {...params}
          label="Search users"
          placeholder="Enter name or SSN"
          InputProps={{
            ...params.InputProps,
            endAdornment: (
              <>
                {isLoading && <CircularProgress color="inherit" size={20} />}
                {params.InputProps.endAdornment}
              </>
            ),
          }}
        />
      )}
      renderOption={(props, option) => (
        <Paper component="li" {...props}>
          <Box sx={{ p: 1 }}>
            <Typography variant="subtitle1">
              {option.firstName} {option.lastName}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              SSN: {option.ssn} | Email: {option.email}
            </Typography>
          </Box>
        </Paper>
      )}
    />
  );
}; 