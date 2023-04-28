import axios from '../custom-axios/axios';
const LibraryService = {
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchBooks: () => {
        return axios.get("/books");
    },

    fetchCountries: () => {
        return axios.get("/countries");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },

    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`)
    },

    addBook: (name,availableCopies, category, authorId) => {
        return axios.post("/books/add", {
            "name": name,
            "availableCopies": availableCopies,
            "category": category,
            "authorId": authorId,
        });
    },
    editBook: (id, name,availableCopies, category, authorId) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "availableCopies": availableCopies,
            "category": category,
            "authorId": authorId
        })
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    markBook: (id) =>{
        return axios.put(`/books/mark/${id}`);
    }
}

export default LibraryService;

