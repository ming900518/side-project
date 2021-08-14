import { AdminInfo } from './adminInfo';
export class User {
  loginPlatform: number;
  ip: string;
  sessionId: string;
  mask: string;
  status: number;
  securityKey: string;
  adminInfo: AdminInfo;
  token: string;
}
