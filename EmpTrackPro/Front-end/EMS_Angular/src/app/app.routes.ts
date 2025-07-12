import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: 'auth', loadChildren: () => import('./components/auth/auth.module').then(m => m.AuthModule) },
  { path: 'employee', loadChildren: () => import('./components/employee/employee.module').then(m => m.EmployeeModule) },
  { path: 'dashboard', loadChildren: () => import('./components/dashboard/dashboard.module').then(m => m.DashboardModule) },
  { path: 'leaves', loadChildren: () => import('./components/leaves/leaves.module').then(m => m.LeavesModule) },
  { path: 'payroll', loadChildren: () => import('./components/payroll/payroll.module').then(m => m.PayrollModule) },
  { path: 'calendar', loadChildren: () => import('./components/calendar/calendar.module').then(m => m.CalendarModule) },
  { path: 'logs', loadChildren: () => import('./components/logs/logs.module').then(m => m.LogsModule) },
  { path: '**', redirectTo: 'auth/login', pathMatch: 'full' }
];