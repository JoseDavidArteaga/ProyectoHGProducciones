@echo off
echo ========================================
echo   HGProducciones - Sistema de Tickets
echo   Configuracion de Seguridad con MySQL
echo ========================================
echo.

echo 1. Verificando XAMPP...
echo   - Asegurate de que XAMPP este ejecutandose
echo   - MySQL debe estar activo en el puerto 3306
echo   - phpMyAdmin debe estar disponible
echo.

echo 2. Base de Datos:
echo   - URL: http://localhost/phpmyadmin/
echo   - Base de datos: hgproducciones_db
echo   - Usuario: root (sin contraseña)
echo.

echo 3. Usuarios por defecto:
echo   ADMINISTRADOR:
echo   - Email: admin@hgproducciones.com
echo   - Contraseña: admin123
echo.
echo   USUARIO DEMO:
echo   - Email: user@hgproducciones.com
echo   - Contraseña: user123
echo.

echo 4. Iniciando Backend...
cd backend
mvn spring-boot:run

pause