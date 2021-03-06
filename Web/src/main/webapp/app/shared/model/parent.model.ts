export interface IParent {
  id?: number;
  name?: string;
  surname?: string;
  address?: string;
  phone?: string;
  email?: string;
  login?: string;
  password?: string;
}

export const defaultValue: Readonly<IParent> = {};
