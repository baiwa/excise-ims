import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { BreadcrumbComponent } from './breadcrumb.component';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule
    ],
    declarations: [
        BreadcrumbComponent
    ],
    exports: [
        // Component
        BreadcrumbComponent,
        // Modules
        CommonModule,
        FormsModule,
        RouterModule
    ]
})
export class BreadcrumbModule { }