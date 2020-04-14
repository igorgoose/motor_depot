package by.schepov.motordepot.builder.impl.carname;

import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.entity.CarBrand;
import by.schepov.motordepot.entity.CarModel;
import by.schepov.motordepot.entity.CarName;
import by.schepov.motordepot.specification.Column;

import java.sql.ResultSet;
import java.sql.SQLException;
//todo delete this
public class ResultSetCarNameBuilder implements Builder<CarName> {

    private CarNameBuilder carNameBuilder = new CarNameBuilder();
    private final ResultSet resultSet;

    public ResultSetCarNameBuilder(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    @Override
    public void reset() {
        carNameBuilder.reset();
    }

    @Override
    public CarName build() {
        return carNameBuilder.build();
    }

    public ResultSetCarNameBuilder withId(Column idColumn) throws SQLException {
        carNameBuilder.withId(resultSet.getInt(idColumn.getName()));
        return this;
    }

    public ResultSetCarNameBuilder withCarModel(Column carModelColumn) throws SQLException {
        carNameBuilder.withCarModel(CarModel.getCarModelByName(resultSet.getString(carModelColumn.getName())));
        return this;
    }

    public ResultSetCarNameBuilder withCarBrand(Column carBrandColumn) throws SQLException {
        carNameBuilder.withCarBrand(CarBrand.getCarBrandByName(resultSet.getString(carBrandColumn.getName())));
        return this;
    }
}
