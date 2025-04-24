export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  ssn: string;
  phone?: string;
  address?: string;
  gender?: string;
  image?: string;
  description?: string;
}

export interface UserSearchResult {
  id: number;
  firstName: string;
  lastName: string;
  ssn: string;
  email: string;
} 