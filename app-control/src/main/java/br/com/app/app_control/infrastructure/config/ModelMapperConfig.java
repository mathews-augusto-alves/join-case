package br.com.app.app_control.infrastructure.config;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Converter converter(ModelMapper modelMapper) {
        return new Converter(modelMapper);
    }

    public static class Converter {

        private final ModelMapper modelMapper;

        public Converter(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public <D, T> Set<D> convertToDTOSet(Collection<T> source, Class<D> targetClass) {
            return source.stream()
                    .map(element -> modelMapper.map(element, targetClass))
                    .collect(Collectors.toSet());
        }

        public <D, T> List<D> convertToDTOList(List<T> source, Class<D> targetClass) {
            return source.stream()
                    .map(element -> modelMapper.map(element, targetClass))
                    .collect(Collectors.toList());
        }

        public <D, T> Page<D> convertToDTOPage(final Page<T> page, final Class<D> dtoClass) {
            return new PageImpl<>(convertToDTOList(page.getContent(), dtoClass), page.getPageable(),
                    page.getTotalElements());
        }

        public <D, T> D convertDTOToEntity(T source, Class<D> targetClass) {
            return modelMapper.map(source, targetClass);
        }

        public <D, T> D convertEntityToDTO(T source, Class<D> targetClass) {
            return modelMapper.map(source, targetClass);
        }
    }
}
