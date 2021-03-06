package managed;

import beans.Correspondrepas;
import managed.util.JsfUtil;
import managed.util.PaginationHelper;
import session.CorrespondrepasFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("correspondrepasController")
@SessionScoped
public class CorrespondrepasController implements Serializable {

    private Correspondrepas current;
    private DataModel items = null;
    @EJB
    private session.CorrespondrepasFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CorrespondrepasController() {
    }

    public Correspondrepas getSelected() {
        if (current == null) {
            current = new Correspondrepas();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CorrespondrepasFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        selectedItemIndex = -1;
        return "List";
    }

    public String prepareView() {
        current = (Correspondrepas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Correspondrepas();
        selectedItemIndex = -1;
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CorrespondrepasCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
public void selectOne() {
        current = (Correspondrepas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        
    }
    public String prepareEdit() {
        current = (Correspondrepas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CorrespondrepasUpdated"));
            current = new Correspondrepas();
           
        selectedItemIndex = -1;
        recreatePagination();
        recreateModel();
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Correspondrepas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        current = new Correspondrepas();
        selectedItemIndex = -1;
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            selectedItemIndex = -1;
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            selectedItemIndex = -1;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CorrespondrepasDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }
    public void reinit() {
     current = new Correspondrepas();
            selectedItemIndex = -1;
        
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
    
    public Correspondrepas getCorrespondrepas(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    

    @FacesConverter(forClass = Correspondrepas.class)
    public static class CorrespondrepasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CorrespondrepasController controller = (CorrespondrepasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "correspondrepasController");
            return controller.getCorrespondrepas(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Correspondrepas) {
                Correspondrepas o = (Correspondrepas) object;
                return getStringKey(o.getEchelle());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Correspondrepas.class.getName());
            }
        }

    }

}
