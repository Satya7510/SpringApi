import { useState, useEffect, useCallback } from 'react';
import { useQuery } from 'react-query';
import { searchUsers } from '@/services/api';
import { UserSearchResult } from '@/types/user';

export const useUserSearch = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [debouncedTerm, setDebouncedTerm] = useState('');

  useEffect(() => {
    const minChars = Number(import.meta.env.VITE_MIN_SEARCH_CHARS);
    if (searchTerm.length < minChars) {
      setDebouncedTerm('');
      return;
    }

    const timer = setTimeout(() => {
      setDebouncedTerm(searchTerm);
    }, Number(import.meta.env.VITE_SEARCH_DEBOUNCE_MS));

    return () => clearTimeout(timer);
  }, [searchTerm]);

  const { data: results, isLoading, error } = useQuery<UserSearchResult[]>(
    ['users', debouncedTerm],
    () => searchUsers(debouncedTerm),
    {
      enabled: debouncedTerm.length >= Number(import.meta.env.VITE_MIN_SEARCH_CHARS),
      staleTime: 30000, // Cache results for 30 seconds
    }
  );

  const handleSearchChange = useCallback((value: string) => {
    setSearchTerm(value);
  }, []);

  return {
    searchTerm,
    results: results || [],
    isLoading,
    error,
    handleSearchChange,
  };
}; 