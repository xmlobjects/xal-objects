module org.xmlobjects.xal {
    requires transitive org.xmlobjects;

    exports org.xmlobjects.xal.adapter;
    exports org.xmlobjects.xal.adapter.deprecated;
    exports org.xmlobjects.xal.adapter.deprecated.helper;
    exports org.xmlobjects.xal.adapter.deprecated.types;
    exports org.xmlobjects.xal.adapter.types;
    exports org.xmlobjects.xal.model;
    exports org.xmlobjects.xal.model.deprecated;
    exports org.xmlobjects.xal.model.deprecated.types;
    exports org.xmlobjects.xal.model.types;
    exports org.xmlobjects.xal.util;
    exports org.xmlobjects.xal.visitor;

    opens org.xmlobjects.xal.model to org.xmlobjects;
    opens org.xmlobjects.xal.model.deprecated to org.xmlobjects;
    opens org.xmlobjects.xal.model.deprecated.types to org.xmlobjects;
    opens org.xmlobjects.xal.model.types to org.xmlobjects;
}