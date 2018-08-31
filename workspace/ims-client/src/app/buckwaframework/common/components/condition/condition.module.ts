import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ConditionComponent } from './condition.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [
        ConditionComponent
    ],
    exports: [
        // Component
        ConditionComponent,
        // Modules
        CommonModule,
        FormsModule
    ]
})
export class ConditionModule { }