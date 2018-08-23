import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ConditionComponent } from './condition/condition.component';
import { DropdownComponent } from './dropdown.component';
import { MessageBarComponent } from './message-bar.component';
import { PaginationComponent } from './pagination.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [
        ConditionComponent,
        DropdownComponent,
        MessageBarComponent,
        PaginationComponent
    ],
    exports: [
        // Components
        ConditionComponent,
        DropdownComponent,
        MessageBarComponent,
        PaginationComponent,
        // Modules
        CommonModule,
        FormsModule
    ]
})
export class ComponentsModule { }