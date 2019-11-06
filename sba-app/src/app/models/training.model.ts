
import { Mentor } from '../models/mentor.model';
import { Skill } from '../models/skill.model';
import { Technology } from '../models/technology.model';
export class Training{
    id: string;
    status: string;
    progress: number;
    fees: string;
    commissionAmount: number;
    rating: number;
    startDate: string;
    endDate: string;
    startTime: string;
    endTime: string;
    amountReceived: number;
    userId: number;
    mentorId: number;
    amountToMentor: number;
    mentor:Mentor=new Mentor();
    skill:Skill=new Skill();
    technology:Technology=new Technology();
} 