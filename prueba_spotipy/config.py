def load_env_vars(filename='.env'):
    env_vars = {}
    try:
        with open(filename, 'r') as file:
            for line in file:
                # Ignora líneas vacías y comentarios
                if line.strip() and not line.startswith("#"):
                    key, value = line.strip().split('=', 1)
                    env_vars[key] = value.strip()
    except FileNotFoundError:
        print(f"Warning: {filename} file not found.")
    return env_vars

# Carga las variables de entorno al importar el módulo
env_vars = load_env_vars()

# Accede directamente a las variables
client_id = env_vars.get("SPOTIFY_CLIENT_ID")
client_secret = env_vars.get("SPOTIFY_CLIENT_SECRET")