import axios from 'axios';
import {create} from 'zustand';

interface Contact {
    id: number;
    name: string;
    gender: string;
    email: string;
    jobTitle: string;
}

interface ContactStore {
    contacts: Contact[];
    fetchContact: () => void;
}
debugger
export const useContactStore = create<ContactStore>((set) => ({
    contacts: [],
    fetchContact: async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/contacts");
            set({contacts: response.data})
        } catch (error) {
            console.error("Failed to fetch contacts:", error);
        }
    },
}))