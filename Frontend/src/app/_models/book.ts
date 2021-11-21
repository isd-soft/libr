export class Book {
  id: string;
  apiId: string;
  title: string;
  authors: Array<string>;
  publisher: string;
  publishedDate: string;
  description: string;
  industryIdentifiers: Array<{ [key: string]: string; }> = [];
  pageCount: number;
  categories: Array<string>;
  averageRating: number;
  ratingsCount: number;
  maturityRating: string;
  imageLinks: { [key: string]: string; } = {};
  language: string;
  previewLink: string;
  comments: Array<string>;
  vote: number;
  userId: number;
  status: string;
  reactions: { [key: string]: string; } = {};
  isManuallyAdded: boolean;


}
