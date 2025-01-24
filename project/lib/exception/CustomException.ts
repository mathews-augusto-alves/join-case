export default class CustomException extends Error {
    title: string;
    errors: string[];

    constructor(title: string, errors: string[]) {
        super(title);

        this.name = this.constructor.name;
        this.title = title;
        this.errors = errors;

        if (Error.captureStackTrace) {
            Error.captureStackTrace(this, CustomException);
        }
    }
}