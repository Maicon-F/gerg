package com.gerg2008.app.factory;

import com.gerg2008.app.model.Alpha_Ideal_oi;
import com.gerg2008.app.model.Alpha_res_oi;
import com.gerg2008.app.model.Component;

import java.util.List;

public class ComponentBuilder {
    Component component;
    public ComponentBuilder(Component c ) {
        this.component = c;
    }

    public ComponentBuilder withAlphaIdealoi(List<Alpha_Ideal_oi> a){
        component.setAIdeal(a);
        return this;
    }

    public ComponentBuilder withAlphaResoi(List<Alpha_res_oi> a){
        component.setARes(a);
        return this;
    }
}
