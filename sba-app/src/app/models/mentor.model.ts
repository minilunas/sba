import { Technology } from '../models/technology.model';
export class Mentor {
    id: number;
    userName: string;
    password: string;
    firstName: string;
    lastName: string;
    email: string;
    contactNumber: number;
    regCode: string;
    role: string;
    linkedinUrl: string;
    yearsofExperience: number;
    active: boolean;
    confirmedSignup: boolean;
    resetPassword: boolean;
    resetPasswordDate: Date;
    rating: number;
    timeZone: string;
    mentorProfile: string;
    starteTime: string;
    endTime: string;
    technology:Technology=new Technology();
    technologyStr:string;
    dateRange:string;
  }
