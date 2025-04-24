import axios from 'axios';
import { User, UserSearchResult } from '@/types/user';

interface ImportMetaEnv {
  readonly VITE_API_BASE_URL: string;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const searchUsers = async (searchTerm: string): Promise<UserSearchResult[]> => {
  try {
    const { data } = await api.get<UserSearchResult[]>(`/users/search?searchTerm=${encodeURIComponent(searchTerm)}`);
    return data;
  } catch (error) {
    console.error('Error searching users:', error);
    return [];
  }
};

export const getUserById = async (id: number): Promise<User | null> => {
  try {
    const { data } = await api.get<User>(`api/users/${id}`);
    return data;
  } catch (error) {
    console.error('Error fetching user:', error);
    return null;
  }
}; 