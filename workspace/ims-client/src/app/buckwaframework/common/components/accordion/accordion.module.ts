import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AccordionComponent } from './accordion.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [
        AccordionComponent
    ],
    exports: [
        // Component
        AccordionComponent,
        // Modules
        CommonModule,
        FormsModule
    ]
})
export class AccordionModule { }