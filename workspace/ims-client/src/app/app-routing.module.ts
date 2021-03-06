import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// service
import { AuthGuard }                from '../services/auth-guard.service';

// pages
import { LoginPage } from '../pages/login/login';
import { HomePage } from '../pages/home/home';
import { MessagePage } from '../pages/message/message';
import { ParameterPage } from '../pages/parameter/parameter';

const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'login',  component: LoginPage },
    { path: 'home', component: HomePage },
    { path: 'message', component: MessagePage, canActivate: [AuthGuard] },
    { path: 'parameter', component: ParameterPage, canActivate: [AuthGuard] }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
