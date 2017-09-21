import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// pages
import { LoginPage } from '../pages/login/login';
import { HomePage } from '../pages/home/home';

const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login',  component: LoginPage },
    { path: 'home', component: HomePage }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
