import {Technology} from "./technology.model";

export class User {
  id: number;
  userName: string;
  password: string;
  firstname: string;
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
  startTime: string;
  endTime: string;
  technology:Technology=new Technology();
  technologyStr:string;
  dateRange:string;
  token:string;
}
